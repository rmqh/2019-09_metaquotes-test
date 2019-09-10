# Requirements
1. Installed Google Chrome browser
2. Allure framework downloaded
> http://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.13.0/

# Run cucumber tests
1. Run all features:
```
mvn clean test -Dtest=RunCucumberTest -Pbrowser-chrome,env-prod,useragent-googlebot
```
2. Run specific features with annotations:
```
mvn clean test -Dtest=RunCucumberTest -Pbrowser-chrome,env-prod,useragent-googlebot -Dcucumber.options="--tags @multiplefilters"
```
# Reports
## 1. Cucumber reports
Check file at
> {project}\target\cucumber-reports\index.html

## 2. Allure reports
Generate and serve test results report
* If allure path set:
```
allure serve
```
* If only zip file downloaded: 
```
D:\Downloads\allure-commandline-2.13.0\allure-2.13.0\bin\allure serve
```

# Logs
> {project}\logs\economic-calendar-test.log
