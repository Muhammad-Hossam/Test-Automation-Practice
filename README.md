# Test Automation Practice

This repository contains my **test automation practice** using **Selenium**, **TestNG**, and **Allure Reports**,  
with integrated **logs** and **screenshots**.

It includes:
- **core (TAF)** → reusable Test Automation Framework  
- **sauce-demo** → demo e-commerce "Test Automation project" that uses the core

---

## 📁 Project Structure
```
Test-Automation-Practice/
│
├── core/          # Framework layer (drivers, utils, logs, screenshots, base classes)
├── sauce-demo/    # Demo e-commerce test project using the core
│   ├── src/test/  # Test cases and page objects
│   ├── logs/      # Logs for each test run
│   ├── screenshots/ # Screenshots 
│   └── allure-results/ # Allure test results
└── README.md
```

---

## 🧰 Tools & Technologies
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Allure Reports**
- **Maven**
- **Log4j** (for logging)

---

## 🚀 How to Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/Muhammad-Hossam/Test-Automation-Practice.git
   cd Test-Automation-Practice
   ```

2. **Build the core framework**
   ```bash
   cd core
   mvn clean install
   ```

3. **Run tests from the demo project**
   ```bash
   cd ../sauce-demo
   mvn test
   ```

4. **Generate Allure Report**
   ```bash
   mvn allure:serve
   ```

---


## 👨‍💻 Author
**Muhammad Hossam**  
[GitHub Profile](https://github.com/Muhammad-Hossam)

---

*This repository act as a journaling for my test automation practice.*
