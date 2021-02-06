import { ComponentFixture, TestBed } from '@angular/core/testing';

import { XhtmlPrikazComponent } from './xhtml-prikaz.component';

describe('XhtmlPrikazComponent', () => {
  let component: XhtmlPrikazComponent;
  let fixture: ComponentFixture<XhtmlPrikazComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ XhtmlPrikazComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(XhtmlPrikazComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
