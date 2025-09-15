package edu.infnet;

public class Main {
    public static void main(String[] args) {

        ApiChallangeClient acc = new ApiChallangeClient();

        question1(acc);
        question2(acc);
        question4(acc);
        question5(acc);
        question6(acc);
        question7(acc);
        question8(acc);
        question11(acc);
//      question3(acc);  As questions 3, 9 e 10 lançam erros propositalmente; por isso, estão comentadas, para não interromper a execução.
//      question9(acc);  Caso queira testar, basta descomentar.
//      question10(acc);
    }

    public static void question1(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 1");
        try {
            System.out.println("\nListando todas as entidades:\n" + apiChallangeClient.getEntities());
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question2(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 2\nListando entidades por ID 2 a 8:");
        try {
            for (int i = 2; i <= 8; i++) {
                System.out.println("\nEntidade com ID " + i + ":");
                System.out.println(apiChallangeClient.getEntityById(i));
            }

        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question3(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 3");
        try {
            System.out.println("\nLançando erro:\n" + apiChallangeClient.getEntityById(13));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question4(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 4");
        try {
            System.out.println("\nChamando api com parâmetros fictícios:\n" + apiChallangeClient.getEntityWithFakeParams("?categoria=teste&limite=5"));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question5(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 5");
        String body = """
                {
                  "name": "Aluno"
                }
                """;

        try {
            System.out.println("\nNova entidade criada:");
            apiChallangeClient.postEntity(body, 11);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question6(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 6");

        try {
            System.out.println("\nRetorno da entidade criada:\n" + apiChallangeClient.getEntityById(11));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question7(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 7");
        String body = """
                {
                  "name": "atualizado"
                }
                """;

        try {
            System.out.println("\nTentando atualizar a entidade 10 com POST:");
            apiChallangeClient.postEntity(body, 10);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question8(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 8");
        String body = """
                {
                  "name": "atualizado"
                }
                """;

        try {
            System.out.println("\nTentando atualizar a entidade 10 com PUT:");
            apiChallangeClient.putEntity(body, 10);
            System.out.println("\nRetorno da entidade atualizada:\n" + apiChallangeClient.getEntityById(10));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question9(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 9");

        try {
            System.out.println("\nEntidade deletada:");
            apiChallangeClient.removeEntity(9);
            System.out.println("\nTentando acessar entidade deletada:");
            apiChallangeClient.getEntityById(9);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question10(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 10");

        try {
            apiChallangeClient.removeEntity(10);
            System.out.println("\nEntidade deletada:\n" + apiChallangeClient.getEntityById(10));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static void question11(ApiChallangeClient apiChallangeClient) {
        System.out.println("\nExercício 11");
        try {
            System.out.println("\nOPTIONS feito para verificação de métodos\n" + apiChallangeClient.optionsEntities());
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}