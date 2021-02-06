import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintSilenceOverviewComponent } from './complaint-silence-overview.component';

describe('OverviewComponent', () => {
  let component: ComplaintSilenceOverviewComponent;
  let fixture: ComponentFixture<ComplaintSilenceOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ComplaintSilenceOverviewComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintSilenceOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
