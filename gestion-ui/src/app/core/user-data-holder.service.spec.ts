import { TestBed } from '@angular/core/testing';

import { UserDataHolderService } from './user-data-holder.service';

describe('UserDataHolderService', () => {
  let service: UserDataHolderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserDataHolderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
