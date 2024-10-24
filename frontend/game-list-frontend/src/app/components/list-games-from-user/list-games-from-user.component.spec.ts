import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListGamesFromUserComponent } from './list-games-from-user.component';

describe('ListGamesFromUserComponent', () => {
  let component: ListGamesFromUserComponent;
  let fixture: ComponentFixture<ListGamesFromUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListGamesFromUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListGamesFromUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
