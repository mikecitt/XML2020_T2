import { TestBed } from '@angular/core/testing';

import { ComplaintSilenceService } from './complaint-silence.service';

describe('ComplaintSilenceService', () => {
  let service: ComplaintSilenceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ComplaintSilenceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
