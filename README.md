# PingIdentity QA Automation Assignment – Web Navigation And Data Extraction

## 📦 Tech Stack

- Java 24
- Selenium WebDriver
- TestNG
- Maven (Build & Dependency Management)
- ExtentReports (Test Reporting)
- RestAssured (for API validation)

---

## 👤 Author

- **Or Kowalsky**
- Assignment for PingIdentity QA Automation Engineer Position

---

## 📁 Project Structure & Features

- Follows **Page Object Model (POM)** for scalability and maintainability
- Modular and readable code organized into:

  - `pages/` – Web element locators
  - `pageutils/` – Page-level action logic
  - `utils/` – Common reusable utilities (e.g., WebDriver logic, formatting)
  - `tests/` – Test classes for WHO and Apple Newsroom sites
  - `POJOs/` – Plain Java objects for structured data

---

## ✅ Implemented Functionality

### WHO News Validation:

- Filters news articles by dropdown option (e.g., "Statement", "Joint News Release")
- Validates:

  - Title is not empty
  - Date is in correct format (`dd/MMMM/yyyy`)
  - URL starts with `https://` and includes the article year

### Apple Newsroom Validation:

- Extracts latest news articles (up to 5)
- Handles Quick Read article types gracefully
- Validates:

  - Title is not empty
  - Date format: `MMMM d, yyyy`
  - URL is valid and absolute

---

### 🦠 WHO COVID-19 Dashboard – Positivity Rate Validation

- Navigates to Table 1.1 and Table 1.3
- Verifies that the latest reporting date is the same in both tables
- Extracts and compares positivity rates for each WHO region

> ⚠ **Note:** The test comparing positivity rates across tables may occasionally fail.  
> This is expected, as the WHO Dashboard may show slightly different values between Table 1.1 and Table 1.3 depending on update timing or internal calculation differences.

---

## 📤 Data Export Feature

- Merges WHO and Apple news articles into a combined list
- Sorts by date (newest to oldest), then by type alphabetically
- Exports to `target/latest_news.txt` in the following format:

```
Origin: {host}
Date: {published_date}
Type: {news_type}
Title: {news_title}
URL: {url}

```

---

## 🚀 How to Run Tests

1. Ensure Java 24 and Maven are installed
2. Navigate to the project root
3. Run:

```sh
mvn clean test
```

Test results will appear in the terminal and also in the `target/surefire-reports/` directory.

---

## 📝 Additional Notes

- Project handles dynamic waits and element visibility checks robustly
- Includes graceful fallbacks and meaningful exception handling
- Designed to be extendable for future news sources or validation scenarios

---

## 🔗 Useful Paths

- Exported News File: `target/latest_news.txt`
- Test Classes:

  - `WhoNewsPageTests.java`
  - `AppleNewsRoomPageTests.java`
  - `WhoCovidSummaryPageTest.java`

---

📨 **Thank you for reviewing this assignment!**
