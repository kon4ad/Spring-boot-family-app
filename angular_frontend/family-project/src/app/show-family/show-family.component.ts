import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  constructor(private route: ActivatedRoute, private Httpsrv: HttpService, private router:Router) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.Httpsrv.readFamily(this.id).subscribe(fam => {
        if(fam == null){
          this.noResults = true;
          alert("Family not found!")
          this.router.navigate(['']);
        }else {
          this.family = fam;
        }
        
      }, err => {
        alert("Family not found!")
        this.router.navigate(['']);
      })
   });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
