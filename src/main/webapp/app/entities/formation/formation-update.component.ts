import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFormation, Formation } from 'app/shared/model/formation.model';
import { FormationService } from './formation.service';

@Component({
  selector: 'jhi-formation-update',
  templateUrl: './formation-update.component.html',
})
export class FormationUpdateComponent implements OnInit {
  isSaving = false;
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    date: [null, [Validators.required]],
    nombre_participant: [null, [Validators.required]],
  });

  constructor(protected formationService: FormationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formation }) => {
      this.updateForm(formation);
    });
  }

  updateForm(formation: IFormation): void {
    this.editForm.patchValue({
      id: formation.id,
      nom: formation.nom,
      date: formation.date,
      nombre_participant: formation.nombre_participant,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formation = this.createFromForm();
    if (formation.id !== undefined) {
      this.subscribeToSaveResponse(this.formationService.update(formation));
    } else {
      this.subscribeToSaveResponse(this.formationService.create(formation));
    }
  }

  private createFromForm(): IFormation {
    return {
      ...new Formation(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      date: this.editForm.get(['date'])!.value,
      nombre_participant: this.editForm.get(['nombre_participant'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormation>>): void {
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
}
