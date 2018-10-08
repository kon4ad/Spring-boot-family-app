import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FatherFormPageComponent } from './father-form-page.component';

describe('FatherFormPageComponent', () => {
  let component: FatherFormPageComponent;
  let fixture: ComponentFixture<FatherFormPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FatherFormPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FatherFormPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
