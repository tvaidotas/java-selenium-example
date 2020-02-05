### For the seleniun part to work you will need the chromedriver

##### You can get it at [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/downloads)
##### Remember to update the path to the chromedriver in the files

### For the reports branch to work

##### Download the following [file](https://drive.google.com/file/d/0ByJmgAhaLx0GVzktVFNNUEZPeWc/view)

##### Everything inside lib and the .jar file need to be added as libraries for the project

##### Extent-config.xml needs to be added to the root of the project

### Running selenium tests on Ubuntu 18.04 VM

Prerequisites:
* maven
* java
* chromium browser (version 77)
* chrome driver (version 77)


Install maven and chromium driver with the script below.

```sudo apt update

sudo apt install -y maven

sudo wget http://security.ubuntu.com/ubuntu/pool/universe/c/chromium-browser/chromium-browser_77.0.3865.120-0ubuntu1~snap1_amd64.deb

sudo apt install ./chromium-browser_77.0.3865.120-0ubuntu1~snap1_amd64.deb
```

Clone the project

Change directory into the project directory

Run ```mvn test```