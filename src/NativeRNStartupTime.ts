// @flow
import type { TurboModule } from 'react-native/Libraries/TurboModule/RCTExport';
import { TurboModuleRegistry } from 'react-native';
import {Int32} from 'react-native/Libraries/Types/CodegenTypes';

export interface Spec extends TurboModule {
    getTimeSinceStartup: () => Promise<Int32>;
}

export default (TurboModuleRegistry.get<Spec>('RNStartupTime')) as Spec;