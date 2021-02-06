import { TestBed } from '@angular/core/testing';

import { ReportGuard } from './report-guard.guard';

describe('ReportGuard', () => {
  let guard: ReportGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ReportGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
