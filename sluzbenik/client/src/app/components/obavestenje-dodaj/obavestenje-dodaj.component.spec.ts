import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjeDodajComponent } from './obavestenje-dodaj.component';

describe('ObavestenjeDodajComponent', () => {
  let component: ObavestenjeDodajComponent;
  let fixture: ComponentFixture<ObavestenjeDodajComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObavestenjeDodajComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObavestenjeDodajComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
