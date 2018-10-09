import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Family } from '../app.component';
import { HttpService } from '../http-serivce';

@Component({
  selector: 'app-show-family',
  templateUrl: './show-family.component.html',
  styleUrls: ['./show-family.component.css']
})
export class ShowFamilyComponent implements OnInit, OnDestroy {
  id: Number;
  family: Family;
  private sub: any;
  noResults: boolean = false;
  constructor(private route: ActivatedRoute, private Httpsrv: HttpService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.Httpsrv.readFamily(this.id).subscribe(fam => {
        if(fam == null){
          this.noResults = true;
        }else {
          this.family = fam;
        }
        
      })
   });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
