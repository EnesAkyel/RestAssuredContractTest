# REST Assured Contract Tests

API contract test suite built with REST Assured and TestNG, targeting the public [Rick and Morty API](https://rickandmortyapi.com). Validates JSON schema contracts, response structure, field values, and error handling across the Characters, Locations, and Episodes endpoints.

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 21 | Language |
| REST Assured | 6.0.0 | HTTP client & assertions |
| TestNG | 7.8.0 | Test runner & data providers |
| Allure | 2.21.0 | Test reporting |
| AspectJ | 1.9.19 | Allure step instrumentation |
| Maven | 3.x | Build & dependency management |

## Project Structure

```
src/test/java/
├── tests/
│   ├── api/                        # Test classes
│   │   ├── GetAllCharactersTest.java
│   │   ├── FilterCharactersTest.java
│   │   ├── GetASingleLocationTest.java
│   │   ├── GetMultipleEpisodesTest.java
│   │   └── NegativeContractTest.java
│   ├── base/
│   │   └── BaseTest.java           # Shared spec setup
│   └── service/
│       └── RequestBuilder.java     # Static spec factory
└── resources/
    ├── test.xml                    # TestNG suite (parallel execution)
    ├── allure.properties
    └── *JsonResponse.json          # JSON Schema draft-07 contract files
```

## What's Tested

**Contract validation** — every response is validated against a JSON Schema (draft-07) to enforce the API contract.

**Layered assertions** — each test asserts HTTP status code, response time (< 3s), schema compliance, and specific field values.

| Test Class | Endpoint | Technique |
|---|---|---|
| `GetAllCharactersTest` | `GET /character` | Schema + pagination metadata + field values |
| `FilterCharactersTest` | `GET /character?name=&status=` | `@DataProvider` with 3 name/status combos; asserts all results match filter |
| `GetASingleLocationTest` | `GET /location/{id}` | `@DataProvider` with 3 location IDs + expected names |
| `GetMultipleEpisodesTest` | `GET /episode/10,28` | Array response — asserts both IDs present with correct fields |
| `NegativeContractTest` | Invalid IDs + no-match filter | 404 status + error body on all resource types |

## Running the Tests

```bash
# Run the full suite
mvn test

# Run and generate Allure report
mvn test allure:report

# Open the Allure report
mvn allure:serve
```

The suite runs test classes in parallel (5 threads) as configured in `test.xml`.

## Allure Report

After running `mvn test allure:report`, open `target/site/allure-maven-plugin/index.html`.

CI uploads the report as a build artifact on every run — see the **Actions** tab.

## CI/CD

GitHub Actions runs the full suite on every push and pull request to `master`. The workflow:

1. Checks out the repo and sets up JDK 21
2. Runs `mvn test`
3. Generates and uploads the Allure report as a downloadable artifact (retained 30 days)

See [`.github/workflows/tests.yml`](.github/workflows/tests.yml).