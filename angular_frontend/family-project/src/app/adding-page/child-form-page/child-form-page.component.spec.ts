import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildFormPageComponent } from './child-form-page.component';

describe('ChildFormPageComponent', () => {
  let component: ChildFormPageComponent;
  let fixture: ComponentFixture<ChildFormPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChildFormPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChildFormPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
