# test-repo

see if you can run it with
 `java -jar immersion_ui-0.0.1-SNAPSHOT.jar`
 
 ## ui build steps:
 1. install nodejs
 2. run `npm install -g @angular/cli@latest`
 3. `cd ui/angular`
 4. `npm install`
 5. `ng build --prod`
 6. `cp convertcsv.json dist`
 7. `cd ..`
 8. `mkdir -p src/main/resources/static`
 9. `cp -r angular/dist/* src/main/resources/static`
 10. `mvn package`

 ## Eureka
 Eureka hostname needs to be `discovery` and linked by ui/backend for this to work.
