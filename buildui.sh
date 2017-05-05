#/usr/bin/bash

cd ui/angular
npm install
ng build --prod
cp usStates.json dist/
cp ca.json dist/
cd ..
mkdir -p src/main/resources/static
cp -r angular/dist/* src/main/resources/static/
