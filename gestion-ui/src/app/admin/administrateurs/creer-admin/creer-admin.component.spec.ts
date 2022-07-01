import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerAdminComponent } from './creer-admin.component';

describe('CreerAdminComponent', () => {
  let component: CreerAdminComponent;
  let fixture: ComponentFixture<CreerAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
