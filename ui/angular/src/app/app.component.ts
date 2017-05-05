import { Component, ElementRef, ViewChild, Input } from '@angular/core';
import { Http } from '@angular/http';
import { ITdDataTableColumn, IPageChangeEvent, TdDataTableService, ITdDataTableSelectEvent, ITdDataTableSortChangeEvent, TdDataTableSortingOrder } from '@covalent/core';
import { MdDialog, MdDialogRef, MdTabChangeEvent, MdSelectChange } from '@angular/material';
import { CreditsComponent } from './credits/credits.component';
import * as d3 from "d3";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  @ViewChild('chart') private chartContainer: ElementRef;
  housingData: Array<any>;
  filteredData: Array<any>;
  filteredTotal: number;
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = '';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  dialogRef: MdDialogRef<any>;

  // for charting
  chartData: Array<any>;
  private margin: any = { top: 20, bottom: 20, left: 20, right: 20 };
  private chart: any;
  private width: number;
  private height: number;
  private colors: any;

  selectedValue: string = 'crim';

  counter: number;

  columns: ITdDataTableColumn[] = [
    { name: 'street', label: 'Street', tooltip: 'Street' },
    { name: 'city', label: 'City', tooltip: 'City' },
    { name: 'zip', label: 'Zip', tooltip: 'Zip' },
    { name: 'state', label: 'State', tooltip: 'State' },
    { name: 'beds', label: 'Beds', tooltip: 'Beds' },
    { name: 'baths', label: 'Baths', tooltip: 'Baths' },
    { name: 'sq__ft', label: 'Square Footage', tooltip: 'Square Footage' },
    { name: 'type', label: 'Type', tooltip: 'Property type' },
    { name: 'sale_date', label: 'Sale Date', tooltip: 'Date of sale' },
    { name: 'price', label: 'Price', tooltip: 'Price' },
    { name: 'latitude', label: 'Latitude', tooltip: 'Latitude' },
    { name: 'longitude', label: 'Longitude', tooltip: 'Longitude' },
  ];

  constructor(private http: Http, private tdDataService: TdDataTableService, private dialog: MdDialog) {
    // get housing data
    this.http.get('/immersion-be/realestate')
    // this.http.get('./convertcsv.json')
      .map(response => response.json().objectList)
      .subscribe(res => {
        this.housingData = res;
        this.filter();
      });

    this.http.get('/immersion-be/realestate/count')
      .map(response => response.json())
      .subscribe(res => this.counter = res);
  }

  ngOnInit() {
  }

  page(pagingEvent: IPageChangeEvent): void {
    this.fromRow = pagingEvent.fromRow;
    this.currentPage = pagingEvent.page;
    this.pageSize = pagingEvent.pageSize;
    this.filter();
  }
  
  sort(sortEvent: ITdDataTableSortChangeEvent): void {
    this.sortBy = sortEvent.name;
    this.sortOrder = sortEvent.order;
    this.filter();
  }

  filter() {
    let newData: Array<any> = this.housingData;
    this.filteredTotal = this.housingData.length;
    newData = this.tdDataService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this.tdDataService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  showCredit() {
    this.dialogRef = this.dialog.open(CreditsComponent);

    this.dialogRef.afterClosed().subscribe(result => {
      this.dialogRef = null;
    });
  }

  onTabChange(tabChangeEvent: MdTabChangeEvent) {
    if (tabChangeEvent.index === 1 && this.housingData && this.housingData.length) {
      this.createChart();
    }
  }

  // for charting...
  createChart() {
    // remove existing
    d3.select("svg").remove();

    let element = this.chartContainer.nativeElement;
    this.width = element.offsetWidth - this.margin.left - this.margin.right;
    this.height = element.offsetHeight - this.margin.top - this.margin.bottom;
    let svg = d3.select(element).append('svg')
      .attr('width', element.offsetWidth)
      .attr('height', element.offsetHeight);

    var projection = d3.geoAlbersUsa()
                   .translate([this.width/2, this.height/2])
    var path = d3.geoPath().projection(projection);

  }

  getTooltip(name: string) {
    for (let i = 0; i < this.columns.length; i++) {
        if (this.columns[i].name === name) {
          return this.columns[i].tooltip;
        }
    }

    return null;
  }
}
