import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObrazloziZalbuComponent } from './obrazlozi-zalbu.component';

describe('ObrazloziZalbuComponent', () => {
  let component: ObrazloziZalbuComponent;
  let fixture: ComponentFixture<ObrazloziZalbuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObrazloziZalbuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObrazloziZalbuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
