import controller.CouponController;
import controller.OrderController;
import controller.UserController;
import data.entity.Coupon;
import data.entity.User;
import data.entity.Order;
import data.repository.*;
import domain.service.*;
import domain.validator.BirthdateValidator;
import domain.validator.Validator;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepo = new UserRepository();
        OrderRepository orderRepo = new OrderRepository();
        CouponRepository couponRepo = new CouponRepository();
        CouponRedemptionRepository redemptionRepo = new CouponRedemptionRepository();

        List<Validator<User>> userValidators = List.of(new BirthdateValidator());
        UserService userService = new UserService(userRepo, userValidators);
        OrderService orderService = new OrderService(orderRepo);
        CouponService couponService = new CouponService(couponRepo, redemptionRepo);
        BirthdateValidator birthValidator = new BirthdateValidator();

        UserController userController = new UserController(userService);
        OrderController orderController = new OrderController(orderService);
        CouponController couponController = new CouponController(couponService);

        // Create a user
        User user = userController.createUser("Ana", "2001-03-01");

        // Create an order
        Order order = orderController.createOrder(user.getId(), 200.0);

        // Create a global coupon
        couponRepo.save(new Coupon(1, "WELCOME10", 10, 5, false));

        // Apply the coupon
        boolean applied = couponController.applyCoupon(order, user, "WELCOME10");

        System.out.println("Coupon applied? " + (applied ? "YES" : "NO"));
        System.out.println("Final price: " + order.getTotalPrice());

        // Try to apply the same coupon again (should work if it's not personal)
        boolean appliedAgain = couponController.applyCoupon(order, user, "WELCOME10");
        System.out.println("Reapplying the same coupon? " + (appliedAgain ? "YES" : "NO"));

        System.out.println("\n--- TEST PERSONAL COUPON ---");

        // Create a personal coupon
        couponRepo.save(new Coupon(2, "ONLYYOU", 5, 20, true));

        // Apply personal coupon for the first time
        Order personalOrder = orderController.createOrder(user.getId(), 100.0);
        boolean personalApplied = couponController.applyCoupon(personalOrder, user, "ONLYYOU");
        System.out.println("Personal coupon applied? " + (personalApplied ? "YES" : "NO"));
        System.out.println("Price after coupon: " + personalOrder.getTotalPrice());

        // Try to apply the same personal coupon again (should be rejected)
        Order secondOrder = orderController.createOrder(user.getId(), 50.0);
        boolean personalAppliedAgain = couponController.applyCoupon(secondOrder, user, "ONLYYOU");
        System.out.println("Reapplying personal coupon? " + (personalAppliedAgain ? "YES" : "NO"));
        System.out.println("Second order final price: " + secondOrder.getTotalPrice());

        System.out.println("\n--- TEST PERSONAL COUPON WITH ANOTHER USER ---");

        // Create another user
        User user2 = userController.createUser("Maria", "1995-05-05");

        // Apply the same personal coupon with a different user
        Order orderUser2 = orderController.createOrder(user2.getId(), 150.0);
        boolean otherUserApplied = couponController.applyCoupon(orderUser2, user2, "ONLYYOU");
        System.out.println("Different user applied personal coupon? " + (otherUserApplied ? "YES" : "NO"));
        System.out.println("Final price (other user): " + orderUser2.getTotalPrice());

        System.out.println("\n--- TEST COUPON USAGE LIMIT (3 users, limit = 2) ---");

        // Create coupon with limit 2
        couponRepo.save(new Coupon(3, "LIMIT2", 2, 30, true));

        // Create two more users
        User user3 = userController.createUser("Luca", "1990-01-01");
        User user4 = userController.createUser("Daria", "2002-09-09");

        // Each user places an order and tries to apply the coupon
        Order order3 = orderController.createOrder(user3.getId(), 100.0);
        Order order4 = orderController.createOrder(user4.getId(), 100.0);
        Order order5 = orderController.createOrder(user2.getId(), 100.0);

        boolean r1 = couponController.applyCoupon(order3, user3, "LIMIT2");  // should be YES
        boolean r2 = couponController.applyCoupon(order4, user4, "LIMIT2");  // should be YES
        boolean r3 = couponController.applyCoupon(order5, user2, "LIMIT2");  // should be NO (limit reached)

        System.out.println("User3 applied coupon? " + (r1 ? "YES" : "NO"));
        System.out.println("User4 applied coupon? " + (r2 ? "YES" : "NO"));
        System.out.println("User2 applied coupon after limit? " + (r3 ? "YES" : "NO"));
    }
}
