import { Component, OnInit, Input } from '@angular/core';
import { Family } from '../../app.component';

@Component({
  selector: 'app-family-table',
  templateUrl: './family-table.component.html',
  styleUrls: ['./family-table.component.css']
})
export class FamilyTableComponent implements OnInit {

  @Input() families: Family[];
  constructor() { }

  ngOnInit() {
  }

}
