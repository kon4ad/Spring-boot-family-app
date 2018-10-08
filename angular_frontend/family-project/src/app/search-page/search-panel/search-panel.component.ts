import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

export interface Sex {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {

  @Output() formSearch: EventEmitter<boolean> = new EventEmitter();
  myForm: FormGroup;
  obj: Object;
  sexs: Sex[] = [
    {value: '0', viewValue: 'Mężczyzna'},
    {value: '1', viewValue: 'Kobieta'}
  ];

  constructor(){}

  ngOnInit() {
    this.myForm = new FormGroup({
      first_name: new FormControl('', [Validators.minLength(2), Validators.maxLength(50)]),
      second_name: new FormControl('', [Validators.minLength(2), Validators.maxLength(50)]),
      pesel: new FormControl('',[Validators.minLength(11), Validators.maxLength(11)]),
      sex: new FormControl(''),
      birth_date: new FormControl('')
    });
  }

  onSubmit(){

    if(this.myForm.status == 'VALID'){
      let obj = Object.create(null);
      let val = this.myForm.getRawValue();
      Object.keys(val).forEach(x => {
        if(val[x] != "" && val[x] != " " && val[x] != null){
          obj[x] = val[x];
        }
      });
      this.formSearch.emit(obj);
    }
  }
}
