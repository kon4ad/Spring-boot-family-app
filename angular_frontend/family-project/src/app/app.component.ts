import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'family-project';
}


export interface Family{
  id: Number;
  father: Father;
  child: Child[];
}

export interface Father{
  id:Number
  firstName: String;
  secondName: String;
  birthDate: Date;
  PESEL: String;
}

export interface Child{
  id:Number
  firstName: String;
  secondName: String;
  sex:String;
  PESEL: String;
}
