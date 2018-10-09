import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-father-form-page',
  templateUrl: './father-form-page.component.html',
  styleUrls: ['./father-form-page.component.css']
})
export class FatherFormPageComponent implements OnInit {
  myForm: FormGroup;
  constructor() { }

  ngOnInit() {
    this.myForm = new FormGroup({
      firstName: new FormControl('', [Validators.minLength(2), Validators.maxLength(50), Validators.required]),
      secondName: new FormControl('', [Validators.minLength(2), Validators.maxLength(50),Validators.required]),
      PESEL: new FormControl('',[Validators.minLength(11), Validators.maxLength(11),Validators.required]),
      birthDate: new FormControl('', Validators.required)
    });
  }

}
