import { atom } from 'recoil';
import { ISerie } from '../interfaces/ISerie';
import { IEpisode } from '../interfaces/IEpisodes';


export const newsSeriesList = atom<ISerie[]>({
  key: 'newsSeriesList',
  default: []
});

export const popularSeriesList = atom<ISerie[]>({
  key: 'popularSeriesList',
  default: []
});

export const allSeriesList = atom<ISerie[]>({
  key: 'allSeriesList',
  default: []
});

export const justOneSerie = atom<ISerie | null>({
  key: 'justOneSerie',
  default: null
});

export const episodesAtSeason = atom<IEpisode[]>({
  key: 'episodesAtSeason',
  default: []
});

export const seasonSelected = atom<number | null>({
  key: 'seasonSelected',
  default: null
});

export const isHome = atom<boolean>({
  key: 'isHome',
  default: true
});