#TU Delft Software Engineering Methods Project

---

## Current build status
[![Waffle](https://badge.waffle.io/Fastjur/SEM-Project.png?label=ready&title=Ready)](https://waffle.io/Fastjur/SEM-Project)
[![Build Status](https://travis-ci.com/Fastjur/SEM-Project.svg?token=ZYkj5b8eBfLppnyaikyc&branch=master)](https://travis-ci.com/Fastjur/SEM-Project)
[![codecov](https://codecov.io/gh/Fastjur/SEM-Project/branch/master/graph/badge.svg?token=3WonhjrCjs)](https://codecov.io/gh/Fastjur/SEM-Project)

Current coverage graph:  
![Graph](https://codecov.io/gh/Fastjur/SEM-Project/branch/master/graph/tree.svg)

---

# Project Summary Report and download

[Please click here](https://fastjur.github.io/SEM-Project) to go to an overview of the project summary and project downloads  
Alternatively [click here](https://github.com/Fastjur/SEM-Project/releases) to go to the latest release or [click here](https://fastjur.github.io/SEM-Project/site/) to go the Project Summary

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
