import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerUserComponent } from './creer-user.component';

describe('CreerUserComponent', () => {
  let component: CreerUserComponent;
  let fixture: ComponentFixture<CreerUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
