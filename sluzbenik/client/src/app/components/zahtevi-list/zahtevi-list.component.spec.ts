import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahteviListComponent } from './zahtevi-list.component';

describe('ZahteviListComponent', () => {
  let component: ZahteviListComponent;
  let fixture: ComponentFixture<ZahteviListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahteviListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZahteviListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
