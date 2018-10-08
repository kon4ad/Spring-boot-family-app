import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http-serivce';
import { Family } from '../app.component';


@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {

  constructor(public httpServ: HttpService){}

  public families: Family[];
  familyFound: boolean = false;
  familyLoading: boolean = false;
  ngOnInit() {
  }

  searchFamily(params){
    this.families = new Array();
    this.familyFound = false;
    this.familyLoading = true;
    this.httpServ.searchChild(params).subscribe(resp => {
      if(resp.length == 0){
        this.familyFound = false;
      }else {
        this.familyFound = true;
        resp.forEach(childID => {
          this.httpServ.readFamilyByChildId(childID).subscribe(family => {
            let add: boolean = true;
            this.families.forEach(fam => {
              if(fam.id == family.id){
                add = false;
              }
            })
            if(add){
              this.families.push(family);
            }
          }
        ),err => {

          }
        });
        this.familyLoading = false;
      }
    },err =>{

    })
  }

}

