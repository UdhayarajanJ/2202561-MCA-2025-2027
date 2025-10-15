import { TestBed } from '@angular/core/testing';

import { ConvertIso } from './convert-iso';

describe('ConvertIso', () => {
  let service: ConvertIso;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConvertIso);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
