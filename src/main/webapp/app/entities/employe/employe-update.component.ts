import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEmploye, Employe } from 'app/shared/model/employe.model';
import { EmployeService } from './employe.service';
import { IFormation } from 'app/shared/model/formation.model';
import { FormationService } from 'app/entities/formation/formation.service';

@Component({
  selector: 'jhi-employe-update',
  templateUrl: './employe-update.component.html',
})
export class EmployeUpdateComponent implements OnInit {
  isSaving = false;
  formations: IFormation[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    formations: [],
  });

  constructor(
    protected employeService: EmployeService,
    protected formationService: FormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employe }) => {
      this.updateForm(employe);

      this.formationService.query().subscribe((res: HttpResponse<IFormation[]>) => (this.formations = res.body || []));
    });
  }

  updateForm(employe: IEmploye): void {
    this.editForm.patchValue({
      id: employe.id,
      nom: employe.nom,
      prenom: employe.prenom,
      adresse: employe.adresse,
      formations: employe.formations,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employe = this.createFromForm();
    if (employe.id !== undefined) {
      this.subscribeToSaveResponse(this.employeService.update(employe));
    } else {
      this.subscribeToSaveResponse(this.employeService.create(employe));
    }
  }

  private createFromForm(): IEmploye {
    return {
      ...new Employe(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      formations: this.editForm.get(['formations'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmploye>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IFormation): any {
    return item.id;
  }

  getSelected(selectedVals: IFormation[], option: IFormation): IFormation {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
