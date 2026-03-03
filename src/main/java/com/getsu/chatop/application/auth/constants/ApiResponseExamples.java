package com.getsu.chatop.application.auth.constants;

public class ApiResponseExamples {

    public static final String VALIDATION_ERROR_EXAMPLE = """
            {
              "status": 400,
              "error": "Validation Error",
              "message": "Le surface de bien est manquante",
              "path": "/api/rentals"
            }
            """;

    public static final String UNAUTHORIZED_ERROR_EXAMPLE = """
            {
              "status": 401,
              "error": "Unauthorized",
              "message": "Token invalide ou expiré",
              "path": "/api/auth/me"
            }
            """;

    public static final String NOT_FOUND_ERROR_EXAMPLE = """
            {
              "status": 404,
              "error": "Not Found",
              "message": "Ressource non trouvée",
              "path": "/api/rentals/999"
            }
            """;

}
