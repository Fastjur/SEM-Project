#TU Delft Software Engineering Methods Project

---

## Current build status
[![Stories in Ready](https://badge.waffle.io/Fastjur/SEM-Project.png?label=ready&title=Ready)](https://waffle.io/Fastjur/SEM-Project)
[![Build Status](https://travis-ci.org/Fastjur/SEM-Project.svg?branch=master)](https://travis-ci.org/Fastjur/SEM-Project)
[![Coverage Status](https://coveralls.io/repos/github/Fastjur/SEM-Project/badge.svg)](https://coveralls.io/github/Fastjur/SEM-Project)

---

# Installing and running the game

1. Clone the repo
2. CD to the root project folder
3. Run `mvn clean install`  
The jar file will now appear in your target folder.
4. Run the game using `java -jar SEM-Group(-1)-Pang-[version]-jar-with-dependencies.jar`

---

# Setting up your IDE

1. Clone the repo
2. CD to the root project folder
3. Run `mvn generate-sources generate-resources compile`
4. Install [Project Lombok](https://projectlombok.org/).
	* For **Eclipse** follow these steps:
		1. Close Eclipse.
		1. Run the following JAR: `.m2/repository/org/projectlombok/lombok/LATEST_LOMBOK_VERSION/lombok-LATEST_LOMBOK_VERSION.jar`.
		1. The installation will provide the required steps to attach Lombok to Eclipse.
	* For **IntelliJ** follow these steps:
		1. "File"
		1. "Settings..."
		1. "Plugins"
		1. "Browse repositories..."
		1. Search for "lombok"
		1. Install "Lombok Plugin"
