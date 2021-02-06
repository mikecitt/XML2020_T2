import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintDecisionOverviewComponent } from './complaint-decision-overview.component';

describe('OverviewComponent', () => {
  let component: ComplaintDecisionOverviewComponent;
  let fixture: ComponentFixture<ComplaintDecisionOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ComplaintDecisionOverviewComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintDecisionOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
