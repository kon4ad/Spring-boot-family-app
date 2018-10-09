import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Father, Child } from '../app.component';
import { HttpService } from '../http-serivce';
import { Router } from '@angular/router';

export interface Sex {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-adding-page',
  templateUrl: './adding-page.component.html',
  styleUrls: ['./adding-page.component.css']
})
export class AddingPageComponent implements OnInit {

  father: Father;
  childrens: Child[];

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  sexs: Sex[] = [
    {value: '0', viewValue: 'Mężczyzna'},
    {value: '1', viewValue: 'Kobieta'}
  ];
  constructor(private _formBuilder: FormBuilder, private httpServ:HttpService, private route:Router) {}

  
  ngOnInit() {
    this.childrens = new Array();
    this.firstFormGroup = this._formBuilder.group({
      firstName: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(2), Validators.pattern('[a-zA-Z]*')]],
      secondName: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(2), Validators.pattern('[a-zA-Z]*')]],
      pesel: ['', [Validators.required, Validators.pattern('[0-9]{11}')]],
      birthDate: ['', [Validators.required]]

    });
    this.secondFormGroup = this._formBuilder.group({
      firstName: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(2), Validators.pattern('[a-zA-Z]*')]],
      secondName: ['', [Validators.required, Validators.maxLength(50), Validators.minLength(2), Validators.pattern('[a-zA-Z]*')]],
      pesel: ['', [Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern('[0-9]{11}')]],
      sex: ['', [Validators.required]]
    });
  }

  createFather(){
    let fath: Father = this.firstFormGroup.getRawValue();
    this.father = fath;
  }

  addChildToList(){
    let child: Child = this.secondFormGroup.getRawValue();
    this.childrens.push(child);
    this.secondFormGroup.reset();
  }

  deleteAllChildrens(){
    this.childrens = new Array();
  }

  addNewFamily(){
    this.httpServ.createFamily().subscribe(familyId => {
      this.httpServ.createFather(this.father, familyId).subscribe(fatherId => {
        let counter:number = 0;
        for(let child of this.childrens){
          this.httpServ.createChild(child, familyId).subscribe(childId => {
            counter++;
            if(counter == this.childrens.length){
              this.route.navigate(['/read/family/', familyId]);
            }
          });

        }
      })
    })
  }

}
