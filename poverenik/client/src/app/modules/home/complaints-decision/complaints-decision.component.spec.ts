import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintsDecisionComponent } from './complaints-decision.component';

describe('ComplaintsDecisionComponent', () => {
  let component: ComplaintsDecisionComponent;
  let fixture: ComponentFixture<ComplaintsDecisionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComplaintsDecisionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintsDecisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
