import { TestBed } from '@angular/core/testing';

import { CsrGuard } from './csr.guard';

describe('CsrGuard', () => {
  let guard: CsrGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CsrGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
