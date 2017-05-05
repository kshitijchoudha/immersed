import { Component, ElementRef, ViewChild, Input } from '@angular/core';
import { Http } from '@angular/http';
import { ITdDataTableColumn, IPageChangeEvent, TdDataTableService, ITdDataTableSelectEvent, ITdDataTableSortChangeEvent, TdDataTableSortingOrder } from '@covalent/core';
import { MdDialog, MdDialogRef, MdTabChangeEvent, MdSelectChange } from '@angular/material';
import { CreditsComponent } from './credits/credits.component';
import * as d3 from "d3";
import * as topojson from "topojson";

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
  usStates: Array<any>;
  california: any;
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

    this.http.get('./usStates.json')
      .map(response => response.json().features)
      .subscribe(res => {
        this.usStates = res;
        console.log(this.usStates);
      });

    this.http.get('./ca.json')
    .map(response => response.json())
    .subscribe(res => this.california = res);

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

    // var color = d3.scaleQuantize()
    //   .range([
    //     "rgb(237,248,233)",
    //     "rgb(186,228,179)",
    //     "rgb(116,196,118)",
    //     "rgb(49,163,84)",
    //     "rgb(0,109,44)"
    //   ]);

    let state = topojson.feature(this.california, this.california.objects.state);
    let counties = topojson.feature(this.california, this.california.objects.counties).features;

    console.log(state);
    console.log(counties);

    let element = this.chartContainer.nativeElement;
    this.width = element.offsetWidth - this.margin.left - this.margin.right;
    this.height = element.offsetHeight - this.margin.top - this.margin.bottom;
    let svg = d3.select(element).append('svg')
      .attr('width', element.offsetWidth)
      .attr('height', element.offsetHeight);

    // chart plot area
    this.chart = svg.append('g')
      .attr('transform', `translate(${this.margin.left}, ${this.margin.top})`);

    var projection = d3.geoAlbersUsa()
                   .translate([this.width/2, this.height/2])
    var path = d3.geoPath().projection(projection);

      this.chart.selectAll("path")
      .data(this.usStates)
      .enter().append("path")
      .attr("d", path)
      .style('stroke', 'white')
      .style('fill', function (d) {
        // get data value
        return "#ccc";
        // if (value) {
        //   // if value exists
        //   return color(value);
        // } else {
        //   // if value is undefined...
        //   return "#ccc";
        // }
      });

      svg.selectAll('circle')
               .data(this.housingData)
               .enter().append('circle')
               .attr('cx', function(d) {
                        return projection([d.longitude, d.latitude])[0];
                 })
               .attr('cy', function(d) {
                    return projection([d.longitude, d.latitude])[1];
               })
               .attr('r', function(d) {
                    return 3.5;
                 })
               .style('fill', 'yellow')
               .style('opacity', 0.75);
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
