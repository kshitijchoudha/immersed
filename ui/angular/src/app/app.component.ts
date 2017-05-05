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
    { name: 'crim', label: 'CRIM', tooltip: 'Per capita crime rate by town' },
    { name: 'zn', label: 'ZN', tooltip: 'Proportion of residential land zoned for lots over 25,000 sq.ft.' },
    { name: 'indus', label: 'INDUS', tooltip: 'Proportion of non-retail business acres per town', numeric: true, format: v => v.toFixed(2) },
    { name: 'chas', label: 'CHAS', tooltip: 'Charles River dummy variable' },
    { name: 'nox', label: 'NOX', tooltip: 'Nitric oxides concentration' },
    { name: 'rm', label: 'RM', tooltip: 'Average number of rooms per dwelling' },
    { name: 'age', label: 'AGE', tooltip: 'Proportion of owner-occupied units built prior to 1940' },
    { name: 'dis', label: 'DIS', tooltip: 'Weighted distances to five Boston employment centres' },
    { name: 'rad', label: 'RAD', tooltip: 'Index of accessibility to radial highways' },
    { name: 'tax', label: 'TAX', tooltip: 'Full-value property-tax rate per $10,000' },
    { name: 'ptratio', label: 'PTRATIO', tooltip: 'Pupil-teacher ratio by town' },
    { name: 'b', label: 'B', tooltip: '1000(Bk - 0.63)^2 where Bk is the proportion of blacks by town' },
    { name: 'lstat', label: 'LSTAT', tooltip: '% lower status of the population' },
    { name: 'medv', label: 'MEDV', tooltip: 'Median value of owner-occupied homes in $1000s' },
  ];

  constructor(private http: Http, private tdDataService: TdDataTableService, private dialog: MdDialog) {
    // get housing data
    this.http.get('/immersion-be/housing')
      .map(response => response.json().objectList)
      .subscribe(res => {
        this.housingData = res;
        this.filter();
      });

    this.http.get('/immersion-be/housing/count')
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
      this.create_data();
      this.createChart();
    }
  }

  selectionChange(changeEvent: MdSelectChange) {
    this.selectedValue = changeEvent.value;

    if (this.housingData && this.housingData.length) {
      this.create_data();
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

    // chart plot area
    this.chart = svg.append('g')
      .attr('transform', `translate(${this.margin.left}, ${this.margin.top})`);

    var xScale = d3.scaleLinear()
        .range([0, this.width]);

    var yScale = d3.scaleLinear()
        .range([this.height, 0]);

    // x & y axis
    var xAxis = d3.axisBottom(xScale);

    var yAxis = d3.axisLeft(yScale);

    this.chartData.forEach(function(d) {
        d.x = +d.x;
        d.y = +d.y;
        d.yhat = +d.yhat;
    });

    var vline = d3.line()
      .x(d => xScale(d['x']))
      .y(d => yScale(d['yhat']));

    xScale.domain(d3.extent(this.chartData, d => d.x));
    yScale.domain(d3.extent(this.chartData, d => d.y));

    this.chart.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + this.height + ")")
        .call(xAxis)
        .append("text")
        .attr("class", "text-label")
        .attr("x", this.width)
        .attr("y", -6)
        .style("text-anchor", "end")
        .style("fill", "rgb(0,0,0)")
        .text(this.getTooltip(this.selectedValue));

    this.chart.append("g")
        .attr("class", "y axis")
        .call(yAxis)
        .append("text")
        .attr("class", "text-label")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em")
        .style("text-anchor", "end")
        .style("fill", "rgb(0,0,0)")
        .text("Median Value in 1000's");

    this.chart.selectAll(".dot")
        .data(this.chartData)
        .enter().append("circle")
        .style("fill", "rgb(122,153,172)")
        .style("stroke", "rgb(41,59,71)")
        .attr("r", 3.5)
        .attr("cx", d => xScale(d.x))
        .attr("cy", d => yScale(d.y));

    this.chart.append("path")
        .datum(this.chartData)
        .attr("class", "line")
        .style("stroke", "rgb(228,0,43)")
        .style("stroke-width", "3")
        .attr("d", vline);
  }

  getTooltip(name: string) {
    for (let i = 0; i < this.columns.length; i++) {
        if (this.columns[i].name === name) {
          return this.columns[i].tooltip;
        }
    }

    return null;
  }

  create_data() {
    var x = [];
    var y = [];
    var n = this.housingData.length;
    var x_mean = 0;
    var y_mean = 0;
    var term1 = 0;
    var term2 = 0;
    var noise_factor = 100;
    var noise = 0;
    // create x and y values
    for (var i = 0; i < n; i++) {
      y.push(this.housingData[i].medv);
      x.push(this.housingData[i][this.selectedValue]);
      x_mean += x[i]
      y_mean += y[i]
    }
    // calculate mean x and y
    x_mean /= n;
    y_mean /= n;

    // calculate coefficients
    var xr = 0;
    var yr = 0;
    for (i = 0; i < x.length; i++) {
      xr = x[i] - x_mean;
      yr = y[i] - y_mean;
      term1 += xr * yr;
      term2 += xr * xr;

    }
    var b1 = term1 / term2;
    var b0 = y_mean - (b1 * x_mean);
    // perform regression 

    let yhat = [];
    // fit line using coeffs
    for (i = 0; i < x.length; i++) {
      yhat.push(b0 + (x[i] * b1));
    }

    var data = [];
    for (i = 0; i < y.length; i++) {
      data.push({
        yhat: yhat[i],
        y: y[i],
        x: x[i]
      })
    }
    this.chartData = data;
  }
}
