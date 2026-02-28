# Rest Assured API Automation Framework

Reusable API automation framework built with **Maven + TestNG + Rest Assured + Gson + Allure**.

## Architecture

- `src/main/java/com/automation/utils`
  - `RestUtil`: reusable wrappers for HTTP methods (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`).
  - `PropertyReader`: centralized configuration reader for `config.properties`.
  - `JsonUtil`: Gson-based reusable serializer/deserializer helpers.

- `src/main/java/com/automation/datahelper`
  - `Headers`: reusable header generators (`getHeader`, `getHeaderForFormData`, `getHeaderWithAuth`).
  - `UrlGenerator`: endpoint map + base URL merger from properties.
  - `TestDataReader`: data-driven test data reader using Gson.

- `src/main/java/com/automation/models`
  - POJOs for request/response serialization and deserialization.

- `src/main/java/com/automation/api/methods`
  - `UserApiMethods`: reusable business-level API methods that combine URL, header, and rest utility layers.

- `src/main/java/com/automation/listeners`
  - `RetryAnalyzer`: retries failed tests once.
  - `TestListener`: TestNG listener that attaches failure/skip details to Allure.

- `src/test/java/com/automation/tests`
  - Test classes that call API methods and validate status code + response payload.

- `src/test/resources/testdata`
  - JSON-based test data for data-driven execution.

## Run tests

```bash
mvn clean test
```

## Allure report

After execution, Allure results are generated under:

```text
target/allure-results
```

Generate/serve report (if Allure CLI is installed):

```bash
allure serve target/allure-results
```
