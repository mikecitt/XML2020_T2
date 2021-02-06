import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintsSilenceComponent } from './complaints-silence.component';

describe('ComplaintsSilenceComponent', () => {
  let component: ComplaintsSilenceComponent;
  let fixture: ComponentFixture<ComplaintsSilenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComplaintsSilenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintsSilenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
