#/usr/bin/bash

cd ui/angular
npm install
ng build --prod
cp convertcsv.json dist/
cd ..
mkdir -p src/main/resources/static
cp -r angular/dist/* src/main/resources/static/
