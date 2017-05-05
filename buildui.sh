#/usr/bin/bash

cd ui/angular
npm install
ng build --prod
cp convertcsv.json dist/
cd ..
cp -r angular/dist/* src/main/resources/static/
