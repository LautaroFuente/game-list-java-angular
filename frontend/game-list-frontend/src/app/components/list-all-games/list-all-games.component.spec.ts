import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAllGamesComponent } from './list-all-games.component';

describe('ListAllGamesComponent', () => {
  let component: ListAllGamesComponent;
  let fixture: ComponentFixture<ListAllGamesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAllGamesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAllGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
