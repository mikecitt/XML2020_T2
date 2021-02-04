import { NgModule } from '@angular/core';

import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzEmptyModule } from 'ng-zorro-antd/empty';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzResultModule } from 'ng-zorro-antd/result';

@NgModule({
  exports: [
    NzButtonModule,
    NzIconModule,
    NzLayoutModule,
    NzGridModule,
    NzSpaceModule,
    NzMenuModule,
    NzFormModule,
    NzInputModule,
    NzCardModule,
    NzEmptyModule,
    NzPopoverModule,
    NzResultModule,
  ],
})
export class AntdModule {}
