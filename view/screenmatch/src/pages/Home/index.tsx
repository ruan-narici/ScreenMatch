import { useRecoilValue, useSetRecoilState } from 'recoil';
import Collection from '../../components/Collection';
import { allSeriesList, episodesAtSeason, newsSeriesList, popularSeriesList } from '../../state/atom';
import { ISerie } from '../../interfaces/ISerie';
import { useChangeHeader, useGetAllSeries, useGetNewsSeries, useGetPopularSeries } from '../../state/hooks';


export default function Home() {
  const listNewsSeries: ISerie[] = useRecoilValue(newsSeriesList);
  const listPopularSeries: ISerie[] = useRecoilValue(popularSeriesList);
  const listAllSeries: ISerie[] = useRecoilValue(allSeriesList);
  const setEpisodesAtSeason = useSetRecoilState(episodesAtSeason);
  
  const getNewsSeries = useGetNewsSeries();
  const getPopularSeries = useGetPopularSeries();
  const getAllSeries = useGetAllSeries();
  const changeHeader = useChangeHeader();

  changeHeader();
  setEpisodesAtSeason([]);
  getNewsSeries();
  getPopularSeries();
  getAllSeries();

  return (
    <>
      <Collection title="Lançamentos no ScreenMatch" series={ listNewsSeries } />
      <Collection title="Títulos populares no ScreenMatch" series={ listPopularSeries } />
      <Collection title="Títulos no ScreenMatch" series={ listAllSeries } />
    </>
  );
}