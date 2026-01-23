

````
src/main/java/com/getsu/chatop
│
├── ReservationApiApplication.java
│
├── config/
│   ├── SecurityConfig.java
│   ├── JwtConfig.java
│   └── SwaggerConfig.java
│
├── auth/
│   ├── controller/
│   │   └── AuthController.java
│   ├── service/
│   │   └── AuthService.java
│   ├── dto/
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   └── AuthResponse.java
│   └── security/
│       ├── JwtFilter.java
│       ├── JwtProvider.java
│       └── UserDetailsServiceImpl.java
│
├── user/
│   ├── controller/
│   │   └── UserController.java
│   ├── service/
│   │   └── UserService.java
│   ├── repository/
│   │   └── UserRepository.java
│   ├── domain/
│   │   └── User.java
│   └── dto/
│       ├── UserResponse.java
│       └── UserUpdateRequest.java
│
├── reservation/
│   ├── controller/
│   │   └── ReservationController.java
│   ├── service/
│   │   └── ReservationService.java
│   ├── repository/
│   │   └── ReservationRepository.java
│   ├── domain/
│   │   └── Reservation.java
│   └── dto/
│       ├── ReservationRequest.java
│       └── ReservationResponse.java
│
├── common/
│   ├── exception/
│   │   ├── ApiException.java
│   │   └── GlobalExceptionHandler.java
│   └── mapper/
│       └── EntityMapper.java
│
└── infrastructure/
    ├── persistence/
    │   └── JpaConfig.java
    └── mysql/
        └── MySQLConfig.java

````