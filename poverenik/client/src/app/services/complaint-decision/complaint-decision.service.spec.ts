import { TestBed } from '@angular/core/testing';

import { ComplaintDecisionService } from './complaint-decision.service';

describe('ComplaintDecisionService', () => {
  let service: ComplaintDecisionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ComplaintDecisionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
