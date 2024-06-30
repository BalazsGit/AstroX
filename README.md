# AstroX

<u><b>Java desktop application with Angular based GUI </u></b><br>

<u><b>Goals:</u></b><br>
- Nice looking possibilities - Angular frontend <br>
- All in one solution 
  - Easy to install/setup/use/launch/review/survey - guided frontend & java backend
  - Bunch together (even 3rd party) signum applications (node/miner/plotter/explorer/nft marketplace/liquidity pool ...).
- Standardized folder structure for applications in a portable way

Built in JCEF (Java Chromium Embedded) maven browser <br>
Launcher, Installer, Updater, Editor, Web Browser ...

This is a java based (java backend) browser using JCEF.

<u><b>Ideas:</u></b><br>
Java application to integrate and bunch signum applications together. <br>
The idea is to bunch everything together to make 
Signum installation to be a well guided user experience attrack new users.

- GUI is build in angular containing an embedded JCEF browser panel (mainFrame -> ngGUIPanel -> JCEF -> Angular GUI).
- Built in JCEF browser within the GUI - tracking #browserContainer size and position to cover it with (mainFrame -> browserPanel -> JCEF).
- BrowserPanel position is set using BoundingClientRectObserver() and @HostListener('window:scroll', ['$event'])

- You can do anything what angular is capable.

- You can control java backend through angular API calls.
  - Install/update/run/setup ... signum node/miner ...
  - Install/update/setup/select database (mariadb, h2, postgress, SQLite ...)
  - Edit config files (text editor, menu for config options to select)

<u><b>Angular build: </u></b><br>
D:\Github\balazsgit\AstroX\src\ngapp> ng build <br>

<u><b>Angular materials: </u></b><br>
ng add @angular/material <br>
https://material.angular.io/

<u><b>Java build: </u></b><br>
D:\Github\balazsgit\AstroX> mvn package <br>

<u><b>angular.json: </u></b><br>
"outputPath": "../../target/ngGUI" <br>
// earlier config: "outputPath": "../main/resources/ngGUI" <br>
"builder": "@angular-builders/custom-webpack:browser" <br>
// original "builder": "@angular-devkit/custom-webpack:browser <br>
"indexTransform": "index-html-transform.js <br>
// use href="./" and remove type="module" from index.html<br>
// use file:// instead of running WEB server http:// or https:// <br>
https://stackoverflow.com/questions/70880595/angular-13-adds-type-module-to-the-js-script-tags-in-index-html

<u><b>Update node.js: </u></b><br>
https://nodejs.org/en/download

node -v >= v18.x.x <br>
nvm -v >= 9.x.x <br>

<u><b>update angular: </u></b><br>
ng update @angular/cli @angular/core --allow-dirty <br>

<u><b>Update bootstrap: </u></b><br>
npm i bootstrap@5.3.1 <br>

<b><u>Update ngx-bootstrap:</u></b><br>
npm i ngx-bootstrap <br>
ng add ngx-bootstrap <br>

<b><u>Update font awesome: </u></b><br>
npm i @fortawesome/angular-fontawesome <br>
npm install @fortawesome/fontawesome-svg-core <br>
npm install @fortawesome/free-solid-svg-icons <br>
ng add @fortawesome/angular-fontawesome <br>

<b><u>java: </u></b><br>
https://openjdk.org/projects/jdk/

<b><u>Environment Variables: </u></b><br>
JAVA_HOME: C:\Program Files\Java\jdk\jdk-21.0.1 <br>
path: %JAVA_HOME%\bin <br>

<b><u>Run java application: </u></b><br>
D:\Github\balazsgit\AstroX\src\ngapp> java -jar .\AstroX-1.0-SNAPSHOT.jar
