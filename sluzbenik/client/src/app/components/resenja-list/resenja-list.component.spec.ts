import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResenjaListComponent } from './resenja-list.component';

describe('ResenjaListComponent', () => {
  let component: ResenjaListComponent;
  let fixture: ComponentFixture<ResenjaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResenjaListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResenjaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
